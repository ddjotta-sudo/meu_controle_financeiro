import 'package:flutter/material.dart';
import '../database/database_helper.dart';
import '../models/transacao.dart';
import 'adicionar_transacao_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final DatabaseHelper dbHelper = DatabaseHelper.instance;
  List<Transacao> transacoes = <Transacao>[];
  double saldo = 0.0;
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    // Agendado após o primeiro frame para evitar problemas com ScaffoldMessenger/context
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _carregarDados();
    });
  }

  Future<void> _carregarDados() async {
    if (!mounted) return;
    setState(() => isLoading = true);
    try {
      final List<Transacao> dados = await dbHelper.lerTodasTransacoes();
      final double saldoAtual = await dbHelper.calcularSaldo();
      if (!mounted) return;
      setState(() {
        transacoes = dados;
        saldo = saldoAtual;
        isLoading = false;
      });
    } catch (e) {
      if (!mounted) return;
      setState(() => isLoading = false);
      // usa maybeOf para evitar exceptions caso Scaffold não esteja disponível
      ScaffoldMessenger.maybeOf(context)?.showSnackBar(
        SnackBar(
          content: Text('Erro ao carregar dados: ${e.toString()}'),
          backgroundColor: Colors.red,
        ),
      );
      debugPrint('Erro carregarDados: $e');
    }
  }

  String _formatarData(DateTime data) {
    return '${data.day.toString().padLeft(2, '0')}/${data.month.toString().padLeft(2, '0')}/${data.year}';
  }

  void _mostrarDetalhes(Transacao transacao) {
    // Campos do model considerados não-nulos conforme instrução: descricao, tipo, categoria, valor, data
    final String descricao = transacao.descricao;
    final String tipo = transacao.tipo;
    final String categoria = transacao.categoria;
    final double valor = transacao.valor;
    final DateTime data = transacao.data;
    final String? observacao = transacao.observacao;

    showDialog<void>(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(descricao),
        content: SingleChildScrollView(
          child: ListBody(
            children: <Widget>[
              Text('Tipo: $tipo'),
              Text('Categoria: $categoria'),
              Text('Valor: R\$ ${valor.toStringAsFixed(2)}'),
              Text('Data: ${_formatarData(data)}'),
              if (observacao != null && observacao.isNotEmpty)
                Text('Observação: $observacao'),
            ],
          ),
        ),
        actions: <Widget>[
          TextButton(
              onPressed: () => Navigator.pop(context),
              child: const Text('Fechar')),
        ],
      ),
    );
  }

  void _confirmarExclusao(Transacao transacao) {
    showDialog<void>(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Excluir Transação'),
        content: Text('Deseja excluir "${transacao.descricao}"?'),
        actions: <Widget>[
          TextButton(
              onPressed: () => Navigator.pop(context),
              child: const Text('Cancelar')),
          TextButton(
            onPressed: () async {
              Navigator.pop(context); // fecha diálogo imediatamente
              try {
                final int? id = transacao.id;
                if (id == null) {
                  if (!mounted) return;
                  ScaffoldMessenger.maybeOf(context)?.showSnackBar(
                    const SnackBar(
                      content: Text('Transação inválida (id nulo).'),
                      backgroundColor: Colors.red,
                    ),
                  );
                  return;
                }
                await dbHelper.deletarTransacao(id);
                if (!mounted) return;
                await _carregarDados();
                if (!mounted) return;
                ScaffoldMessenger.maybeOf(context)?.showSnackBar(
                  const SnackBar(
                    content: Text('Transação excluída!'),
                    backgroundColor: Colors.green,
                  ),
                );
              } catch (e) {
                if (!mounted) return;
                ScaffoldMessenger.maybeOf(context)?.showSnackBar(
                  SnackBar(
                    content: Text('Erro ao excluir: ${e.toString()}'),
                    backgroundColor: Colors.red,
                  ),
                );
                debugPrint('Erro excluir transação: $e');
              }
            },
            child: const Text('Excluir', style: TextStyle(color: Colors.red)),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(
        title: const Text('Controle Financeiro'),
        backgroundColor: theme.colorScheme.primary,
        foregroundColor: theme.colorScheme.onPrimary,
      ),
      body: isLoading
          ? const Center(child: CircularProgressIndicator())
          : Column(
              children: <Widget>[
                Container(
                  width: double.infinity,
                  margin: const EdgeInsets.all(16),
                  padding: const EdgeInsets.all(24),
                  decoration: BoxDecoration(
                    gradient: LinearGradient(
                      colors: saldo >= 0
                          ? [Colors.green, Colors.greenAccent.shade700]
                          : [Colors.red, Colors.redAccent.shade700],
                    ),
                    borderRadius: BorderRadius.circular(16),
                  ),
                  child: Column(
                    children: <Widget>[
                      const Text(
                        'Saldo Atual',
                        style: TextStyle(color: Colors.white, fontSize: 18),
                      ),
                      const SizedBox(height: 8),
                      Text(
                        'R\$ ${saldo.toStringAsFixed(2)}',
                        style: const TextStyle(
                            color: Colors.white,
                            fontSize: 36,
                            fontWeight: FontWeight.bold),
                      ),
                    ],
                  ),
                ),
                Expanded(
                  child: transacoes.isEmpty
                      ? const Center(
                          child: Text('Nenhuma transação registrada',
                              style:
                                  TextStyle(fontSize: 16, color: Colors.grey)),
                        )
                      : ListView.builder(
                          itemCount: transacoes.length,
                          itemBuilder: (context, index) {
                            final Transacao transacao = transacoes[index];
                            final String tipoLower =
                                transacao.tipo.toLowerCase();
                            final bool isEntrada = tipoLower == 'entrada';
                            final String descricao = transacao.descricao;
                            final String categoria = transacao.categoria;
                            final double valor = transacao.valor;
                            final DateTime data = transacao.data;

                            return Card(
                              margin: const EdgeInsets.symmetric(
                                  horizontal: 16, vertical: 8),
                              child: ListTile(
                                leading: CircleAvatar(
                                  backgroundColor:
                                      isEntrada ? Colors.green : Colors.red,
                                  child: Icon(
                                      isEntrada
                                          ? Icons.arrow_downward
                                          : Icons.arrow_upward,
                                      color: Colors.white),
                                ),
                                title: Text(descricao),
                                subtitle:
                                    Text('$categoria • ${_formatarData(data)}'),
                                trailing: Row(
                                  mainAxisSize: MainAxisSize.min,
                                  children: <Widget>[
                                    IconButton(
                                      tooltip: 'Editar',
                                      icon: Icon(Icons.edit,
                                          color: theme.colorScheme.primary),
                                      onPressed: () async {
                                        await Navigator.push(
                                          context,
                                          MaterialPageRoute(
                                            builder: (context) =>
                                                const AdicionarTransacaoScreen(
                                                    existente: transacao),
                                          ),
                                        );
                                        if (mounted) await _carregarDados();
                                      },
                                    ),
                                    IconButton(
                                      tooltip: 'Excluir',
                                      icon: const Icon(Icons.delete,
                                          color: Colors.red),
                                      onPressed: () =>
                                          _confirmarExclusao(transacao),
                                    ),
                                  ],
                                ),
                                onTap: () => _mostrarDetalhes(transacao),
                                onLongPress: () =>
                                    _confirmarExclusao(transacao),
                              ),
                            );
                          },
                        ),
                ),
              ],
            ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          await Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) =>
                    const AdicionarTransacaoScreen(existente: null)),
          );
          if (mounted) await _carregarDados();
        },
        backgroundColor: theme.colorScheme.primary,
        child: const Icon(Icons.add, color: Colors.white),
      ),
    );
  }
}

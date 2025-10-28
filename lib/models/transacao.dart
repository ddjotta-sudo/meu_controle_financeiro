class Transacao {
  final int? id;
  final String descricao;
  final double valor;
  final String tipo; // 'entrada' ou 'saida'
  final String categoria;
  final DateTime data;
  final String? observacao;

  Transacao({
    this.id,
    required this.descricao,
    required this.valor,
    required this.tipo,
    required this.categoria,
    required this.data,
    this.observacao,
  });

  factory Transacao.fromMap(Map<String, dynamic> map) {
    return Transacao(
      id: map['id'] as int?,
      descricao: map['descricao'] as String,
      valor: (map['valor'] as num).toDouble(),
      tipo: map['tipo'] as String,
      categoria: map['categoria'] as String,
      data: DateTime.parse(map['data'] as String),
      observacao: (map['observacao'] as String?)?.isEmpty == true
          ? null
          : map['observacao'] as String?,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'descricao': descricao,
      'valor': valor,
      'tipo': tipo,
      'categoria': categoria,
      'data': data.toIso8601String(),
      'observacao': observacao ?? '',
    };
  }
}

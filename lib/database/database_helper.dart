import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart' as p;
import '../models/transacao.dart';

class DatabaseHelper {
  DatabaseHelper._privateConstructor();
  static final DatabaseHelper instance = DatabaseHelper._privateConstructor();

  static Database? _database;

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDB('controle_financeiro.db');
    return _database!;
  }

  Future<Database> _initDB(String fileName) async {
    final dbPath = await getDatabasesPath();
    final path = p.join(dbPath, fileName);

    return await openDatabase(
      path,
      version: 1,
      onCreate: _onCreate,
      // Se precisar de migrações futuras, use onUpgrade
    );
  }

  Future<void> _onCreate(Database db, int version) async {
    await db.execute('''
      CREATE TABLE transacoes (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        descricao TEXT NOT NULL,
        valor REAL NOT NULL,
        tipo TEXT NOT NULL,
        categoria TEXT NOT NULL,
        data TEXT NOT NULL,
        observacao TEXT
      )
    ''');
  }

  // CREATE
  Future<int> criarTransacao(Transacao t) async {
    final db = await database;
    return await db.insert('transacoes', t.toMap());
  }

  // READ - todas (ordenadas por data desc)
  Future<List<Transacao>> lerTodasTransacoes() async {
    final db = await database;
    final res = await db.query(
      'transacoes',
      orderBy: 'date(data) DESC, id DESC',
    );
    return res.map((m) => Transacao.fromMap(m)).toList();
  }

  // UPDATE
  Future<int> atualizarTransacao(Transacao t) async {
    final db = await database;
    return await db.update(
      'transacoes',
      t.toMap(),
      where: 'id = ?',
      whereArgs: [t.id],
    );
  }

  // DELETE
  Future<int> deletarTransacao(int id) async {
    final db = await database;
    return await db.delete(
      'transacoes',
      where: 'id = ?',
      whereArgs: [id],
    );
  }

  // SALDO (entradas - saídas)
  Future<double> calcularSaldo() async {
    final db = await database;

    // total entradas
    final e = await db.rawQuery(
        "SELECT SUM(valor) as total FROM transacoes WHERE tipo = 'entrada'");
    final entradas = (e.first['total'] as num?)?.toDouble() ?? 0.0;

    // total saídas
    final s = await db.rawQuery(
        "SELECT SUM(valor) as total FROM transacoes WHERE tipo = 'saida'");
    final saidas = (s.first['total'] as num?)?.toDouble() ?? 0.0;

    return entradas - saidas;
  }
}

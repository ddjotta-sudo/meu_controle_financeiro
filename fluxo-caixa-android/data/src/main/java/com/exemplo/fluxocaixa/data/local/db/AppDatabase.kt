package com.exemplo.fluxocaixa.data.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exemplo.fluxocaixa.data.local.db.dao.TransactionDao
import com.exemplo.fluxocaixa.data.local.db.dao.FeeDao
import com.exemplo.fluxocaixa.domain.model.Transaction
import com.exemplo.fluxocaixa.domain.model.Fee

@Database(entities = [Transaction::class, Fee::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun feeDao(): FeeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cashflow_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
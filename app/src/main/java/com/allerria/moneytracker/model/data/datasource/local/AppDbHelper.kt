package com.allerria.moneytracker.model.data.datasource.local

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.content.Context
import com.allerria.moneytracker.QueryWrapper
import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.Wallets
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.create
import com.squareup.sqldelight.db.SqlDatabase
import javax.inject.Inject

open class AppDbHelper @Inject constructor(context: Context) : SupportSQLiteOpenHelper.Callback(QueryWrapper.version) {

    val wrapper: QueryWrapper = createQueryWrapper(QueryWrapper.create(
            context = context,
            name = "app.db",
            callback = this
    ))

    private fun createQueryWrapper(database: SqlDatabase): QueryWrapper {
        return QueryWrapper(
                database = database,
                transactionsAdapter = Transactions.Adapter(
                        typeAdapter = EnumColumnAdapter(),
                        currencyAdapter = EnumColumnAdapter(),
                        dateAdapter = DateAdapter()
                ),
                walletsAdapter = Wallets.Adapter(
                        typeAdapter = EnumColumnAdapter(),
                        currencyAdapter = EnumColumnAdapter()
                )
        )
    }

    override fun onCreate(db: SupportSQLiteDatabase) {
        val sqlDatabase = QueryWrapper.create(db)
        val queryWrapper = createQueryWrapper(sqlDatabase)
        QueryWrapper.onCreate(sqlDatabase.getConnection())
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        db.execSQL("PRAGMA foreign_keys=ON")
    }

    override fun onUpgrade(
            db: SupportSQLiteDatabase,
            oldVersion: Int,
            newVersion: Int
    ) {
        // here may be upgrade logic
    }

}
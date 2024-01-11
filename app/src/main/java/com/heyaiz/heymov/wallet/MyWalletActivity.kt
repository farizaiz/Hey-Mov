package com.heyaiz.heymov.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.heyaiz.heymov.R
import com.heyaiz.heymov.wallet.adapter.WalletAdapter
import com.heyaiz.heymov.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        dataList.add(
           Wallet(
               "Avenger returns",
               "Sabtu, 12 Jan 2023",
               70000.0,
               "0"
           )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Sabtu, 12 Jan 2023",
                710000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Avenger",
                "Sabtu, 12 Jan 2023",
                20000.0,
                "0"
            )
        )

        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){

        }

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletTopUpActivity::class.java))
        }

    }
}

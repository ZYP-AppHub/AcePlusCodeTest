package com.zyp.codetest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zyp.codetest.R
import com.zyp.codetest.databinding.ActivityUserDetailBinding
import com.zyp.codetest.model.User

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        val userData: User? = extras?.getParcelable("user_data")
        if (userData != null) {
            binding.txtName.text = "Name : \n" + userData.name
            binding.txtEmail.text = "Email : \n" + userData.email
            binding.txtAddress.text = "Address : \n" +
                    "" + userData.address.city + "\n" +
                    "" + userData.address.street + "\n" +
                    "" + userData.address.suite + "\n" +
                    "" + userData.address.zipcode

            binding.txtPhone.text = "Phone : \n" + userData.phone
            binding.txtWebsite.text = "Website : \n" + userData.website
        }
    }


}

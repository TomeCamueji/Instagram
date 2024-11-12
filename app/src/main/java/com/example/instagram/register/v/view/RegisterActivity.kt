package com.example.instagram.register.v.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.commom.view.CropperImageFragment
import com.example.instagram.commom.view.CropperImageFragment.Companion.KEY_URI
import com.example.instagram.databinding.ActivityRegisterBinding
import com.example.instagram.main.view.MainActivity
import com.example.instagram.register.v.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.example.instagram.register.v.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.io.File
import java.io.IOException
import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterActivity : AppCompatActivity(),FragmentAttachListener {

    private lateinit var binding:ActivityRegisterBinding
    private lateinit var currentPhoto : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)

//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.register_fragment,fragment)
//            commit()
//        }
    }

    override fun goToNameAmdPasswordScreen(email: String) {
        val args = Bundle()
        args.putString(KEY_EMAIL, email)

        val fragment = RegisterNamePasswordFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goToWelcomeScreen(name: String) {
        val args = Bundle()
        args.putString(KEY_NAME,name)

        val fragment = RegisterWelcomeFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goToPhotoScreen() {
        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }

    override fun goToMainScree() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){uri : Uri?->
//        if (uri != null) {
//            openImageCropper(uri)
//        }
        uri?.let {
            openImageCropper(it)
        }
    }
    override fun goToGaleryScreen() {
        getContent.launch("image/*")
    }

    private val getCamera = registerForActivityResult(ActivityResultContracts.TakePicture()){ saved ->
        if (saved){
            openImageCropper(currentPhoto)
        }

    }

    override fun goToCameraScreen() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null){
          val photoFile: File? = try {
                createImageFile()
            }catch (e: IOException){
                Log.w("RegisterActivity", e.message, e)
              null
            }

            photoFile?.also {
                val photoUri = FileProvider.getUriForFile(this, "com.example.instagram.fileprovider",it)
                currentPhoto = photoUri
                getCamera.launch(photoUri)
            }
        }
    }
    @Throws(IOException::class)
    private fun createImageFile():File{
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Date())
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_",".jpg", dir)
    }

    private fun replaceFragment(fragment:Fragment){
        if (supportFragmentManager.findFragmentById(R.id.register_fragment) ==null){
            supportFragmentManager.beginTransaction().apply {
                add(R.id.register_fragment,fragment)
                commit()
            }
        }else{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.register_fragment,fragment)
                addToBackStack(null)
                commit()
            }
        }

    }
    private fun openImageCropper(uri:Uri){
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_URI, uri)
            }
        }
        replaceFragment(fragment)
    }
}
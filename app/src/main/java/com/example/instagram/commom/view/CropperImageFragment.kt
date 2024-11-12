package com.example.instagram.commom.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentImageCropperBinding
import java.io.File

class CropperImageFragment:Fragment(R.layout.fragment_image_cropper) {
    private  var binding: FragmentImageCropperBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentImageCropperBinding.bind(view)

        val uri = arguments?.getParcelable<Uri>(KEY_URI)

        binding?.let {
            with(it){

                cropperContainer.setAspectRatio(1,1)
                cropperContainer.setFixedAspectRatio(true)
                cropperContainer.setImageUriAsync(uri)

                cropperBtnCancel.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

              cropperBtnSave.setOnClickListener {
                  val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                  if (dir != null){
                      val uriTosaved = Uri.fromFile(File(dir.path, "${System.currentTimeMillis()}.jpeg"))
                      
                  }
              }
            }
        }
    }
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object{
        const val KEY_URI ="key_uri"
    }
}
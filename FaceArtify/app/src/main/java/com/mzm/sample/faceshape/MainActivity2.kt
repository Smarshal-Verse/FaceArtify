package com.mzm.sample.faceshape

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mzm.sample.cartoongan.MainActivity
import com.mzm.sample.cartoongan.R
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class MainActivity2 : AppCompatActivity() {
    private lateinit var select_image_button : Button
    private lateinit var make_prediction : Button
    private lateinit var img_view : ImageView
    private lateinit var predictedLabel : String
    private lateinit var text_view : TextView
   private lateinit var camerabtn : Button
   private lateinit var camerabtn2 : Button
    var bitmap: Bitmap? = null
    var uri:String = ""
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        select_image_button =  findViewById(R.id.button)
        make_prediction = findViewById(R.id.button2)
        img_view = findViewById(R.id.imageView2)
        text_view = findViewById(R.id.textView)
        camerabtn = findViewById(R.id.camerabtn)
        camerabtn2 = findViewById(R.id.camerabtn2)
        checkandGetpermissions()


        select_image_button.setOnClickListener {
            Log.d("mssg", "button pressed")
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 250)
        }

        camerabtn.setOnClickListener {
            var camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(camera, 200)
        }
        camerabtn2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkandGetpermissions(){
        if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 100)
        }
        else{
            Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SuspiciousIndentation")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(requestCode == 250){
            img_view.setImageURI(data?.data)

            var uuri : Uri?= data?.data
            uri = uuri.toString()
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uuri)
        }
        else if(requestCode == 200 && resultCode == Activity.RESULT_OK){
            bitmap = data?.extras?.get("data") as Bitmap
            img_view.setImageBitmap(bitmap)
        }
        make_prediction.setOnClickListener {
            makeprediction()
        }

    }
    fun ARGBBitmap(img: Bitmap): Bitmap {
        return img.copy(Bitmap.Config.ARGB_8888, true)
    }
    fun makeprediction() {
        val image = TensorImage.fromBitmap(ARGBBitmap(bitmap!!)).bitmap
        val inputWidth = 250
        val inputHeight = 190
        val inputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, inputHeight, inputWidth, 1), DataType.FLOAT32)
        val resizedBitmap = Bitmap.createScaledBitmap(image, inputWidth, inputHeight, true)
        val pixelValues = convertBitmapToFloatArray(resizedBitmap)
        inputBuffer.loadArray(pixelValues)
        val inputTensorBuffer = inputBuffer.buffer
        val tfliteModel = FileUtil.loadMappedFile(applicationContext, "face_shape_recognizer.tflite")
        val tflite: Interpreter = Interpreter(tfliteModel)
        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 5), DataType.FLOAT32)
        tflite.run(inputTensorBuffer, outputBuffer.buffer)
        val labels = listOf("Heart", "Oblong", "Oval", "Round", "Square")
        val outputArray = outputBuffer.floatArray
        val maxIndex = outputArray.indices.maxBy { outputArray[it] } ?: -1
        predictedLabel = if (maxIndex != -1) labels[maxIndex]
        else null.toString()   // Return null if there's no face detected
        text_view.text = predictedLabel ?: "Null" // Set text_view to "Null" if predictedLabel is null
        tflite.close()

    }

    fun convertBitmapToFloatArray(bitmap: Bitmap): FloatArray {
        val numPixels = bitmap.width * bitmap.height
        val pixels = IntArray(numPixels)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        val floatValues = FloatArray(numPixels)
        for (i in 0 until numPixels) {
            floatValues[i] = (pixels[i] and 0xFF) / 255.0f
        }
        return floatValues
    }


}
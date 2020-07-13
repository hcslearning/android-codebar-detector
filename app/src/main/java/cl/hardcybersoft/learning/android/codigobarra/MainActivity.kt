package cl.hardcybersoft.learning.android.codigobarra

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var boton:Button = findViewById<Button>(R.id.boton)
        boton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val imagenCodigoBarras:Bitmap? = getImagenCodigoBarras()
                val codigoString:String = leerCodigoBarras(imagenCodigoBarras!!)
                findViewById<TextView>(R.id.texto).setText(codigoString)
            }
        })
    }

    private fun getImagenCodigoBarras():Bitmap? {
        val drawable:BitmapDrawable? = ContextCompat.getDrawable(this, R.drawable.barcode) as BitmapDrawable
        return drawable?.bitmap
    }

    private fun leerCodigoBarras(imagenCodigoBarras:Bitmap): String {
        var barcodeDetector:BarcodeDetector = BarcodeDetector.Builder(this).build()
        val frame:Frame = Frame.Builder().setBitmap( imagenCodigoBarras ).build()
        val barcodes:SparseArray<Barcode> = barcodeDetector.detect( frame )
        return barcodes.valueAt(0).rawValue
    }
}
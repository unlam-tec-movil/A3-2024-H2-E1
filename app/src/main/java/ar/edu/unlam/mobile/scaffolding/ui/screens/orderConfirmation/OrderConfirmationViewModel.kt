package ar.edu.unlam.mobile.scaffolding.ui.screens.orderConfirmation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderConfirmationViewModel
    @Inject
    constructor(
        private val orderRepository: UserOrderRepository,
    ) : ViewModel() {
        init {
            println("==MESA SELECCIONADA: ${orderRepository.getTable()}")
            println("==JSON para QR: ${orderRepository.toJson()}")
        }

        fun clearOrder() {
            orderRepository.removeAllItems()
            orderRepository.clearTable()
        }

        fun generateQrCode(json: String): Bitmap? {
            val matrix = QRCodeWriter().encode(json, BarcodeFormat.QR_CODE, 500, 500)
            val width = matrix.width
            val height = matrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (matrix.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
                }
            }
            return bitmap
        }

        fun getJSONString(): String = orderRepository.toJson()
    }

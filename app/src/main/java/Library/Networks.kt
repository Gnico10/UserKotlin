package Library

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class Networks(activity: Activity) : AppCompatActivity() {
    private var _activity: Activity? = null

    init{
        _activity = activity
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun verificarNetworks(): Boolean{
        var valor = false
        //Verificar los servicios de conectividad.
        val cm = _activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(cm != null){
            // Se obtiene la capacidad de conectividad del dispositivo.
            val nc = cm.getNetworkCapabilities(cm.activeNetwork)
            if(nc != null){
                //Conexión a traves de datos móviles o wifi.
                if(nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                        nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    valor = true
                }
            }
        }

        return valor
    }
}
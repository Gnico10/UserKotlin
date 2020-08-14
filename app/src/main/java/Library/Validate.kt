package Library

import java.util.regex.Matcher
import java.util.regex.Pattern

class Validate {
    companion object{
        fun isEmail(email:String): Boolean{
            // reglas de expresión regular
            var pat = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")
            var mat = pat!!.matcher(email)
            return mat!!.find()
        }

        fun isPassword(password:String): Boolean{
            return password.length >= 6
        }
    }
}
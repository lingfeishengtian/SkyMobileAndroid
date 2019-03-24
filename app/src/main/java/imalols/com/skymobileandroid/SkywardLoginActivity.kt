package imalols.com.skymobileandroid

import android.app.LoaderManager.LoaderCallbacks
import android.content.Context
import android.content.Loader
import android.database.Cursor
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_skyward_login.*
import java.lang.Exception

/**
 * A login screen that offers login via email/password.
 */
class SkywardLoginActivity : AppCompatActivity(), LoaderCallbacks<Cursor> {
    var SkywardWebsite: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("HEHLELELO")
        setContentView(R.layout.activity_skyward_login)

        val Skyward = WebView(this)

        Skyward.layoutParams = parentView.layoutParams
        email_login_form.addView(Skyward)
        Skyward.loadUrl("https://skyward-fbprod.iscorp.com/scripts/wsisa.dll/WService=wsedufortbendtx/seplog01.w")
        Skyward.settings.javaScriptEnabled = true
        Skyward.settings.javaScriptCanOpenWindowsAutomatically = true
        Skyward.settings.setSupportMultipleWindows(true)

        email_sign_in_button.setOnClickListener{
            AttemptLoginToSkyward(username.text.toString(), password.text.toString())
        }

        SkywardWebsite = Skyward
    }

    fun AttemptLoginToSkyward(Username: String, Passcode: String){
        val Javascript = "login.value = \"$Username\"; password.value = \"$Passcode\"; bLogin.click();"
        SkywardWebsite!!.evaluateJavascript(Javascript){
            println("This isn't supposed to be here")
            val javascrip1t = """
                        function check(){
                        if(msgBodyCol.textContent == "Invalid login or password."){
                        msgBtn1.click()
                        return "fail"
                        } else if(dMessage.attributes["style"].textContent.includes("visibility: visible")) {
                        msgBtn1.click()
                        return "clicked"
                        }else if(document.querySelector(".ui-widget").textContent.includes("Please wait")){
                        return "Loading"
                        }else{
                        return "oop"
                        }
                        }
                        check()
                        """
            SkywardWebsite!!.evaluateJavascript(javascrip1t){it
                try{
                    println(it + "WHY")
                }catch (e: Exception){
                    println("Well that was an error")
                }
            }
        }
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadFinished(p0: Loader<Cursor>?, p1: Cursor?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoaderReset(p0: Loader<Cursor>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

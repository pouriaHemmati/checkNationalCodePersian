package com.national.pouria

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import kotlinx.android.synthetic.main.content_main.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import java.lang.String


class MainActivity : AppCompatActivity() {


    val STARTUP_DELAY = 300
    val ANIM_ITEM_DURATION = 5000
    val EDITTEXT_DELAY = 300
    val BUTTON_DELAY = 500
    val VIEW_DELAY = 400


    val decelerateInterpolator = DecelerateInterpolator()
    var checkNationalCode = CheckNationalCode().getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)


        btn_done?.let { ViewCompat.animate(it) }
                ?.scaleY(1f)?.scaleX(1f)
                ?.setStartDelay(((BUTTON_DELAY) + 500).toLong())
                ?.setDuration(500)
                ?.setInterpolator(decelerateInterpolator)
                ?.start()

        animation_view_login?.let { ViewCompat.animate(it) }
                ?.scaleY(1f)?.scaleX(1f)
                ?.setStartDelay(((VIEW_DELAY) + 500).toLong())
                ?.setDuration(500)
                ?.setInterpolator(decelerateInterpolator)
                ?.start()


        btn_done.setOnClickListener{
            if (checkNationalCode.checkNationalCode(national_code.text.toString())) {
                txt_result.text = "is valid"
            } else {
                txt_result.text = "is not valid"
            }
        }
//
//        //Here we are setting animation on displaying content
//        for (i in 1 until container.childCount) {
//            val v = container.getChildAt(i)
//            var viewAnimator: ViewPropertyAnimatorCompat
//                 viewAnimator = if (v is EditText) {
//                ViewCompat.animate(v)
//                        .scaleY(1f).scaleX(1f)
//                        .setStartDelay((EDITTEXT_DELAY * i + 500).toLong())
//                        .setDuration(500)
//            } else if (v is Button) {
//                ViewCompat.animate(v)
//                        .scaleY(1f).scaleX(1f)
//                        .setStartDelay((BUTTON_DELAY * i + 500).toLong())
//                        .setDuration(500)
//            } else {
//                ViewCompat.animate(v)
//                        .translationY(50f).alpha(1f)
//                        .setStartDelay((VIEW_DELAY * i + 500).toLong())
//                        .setDuration(1000)
//            }
//            viewAnimator.setInterpolator(DecelerateInterpolator()).start()
//        }
//
//        // text watcher
        national_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.length > 10) {
                        txt_result.text = ""
                    }
                }
            }
        })

        animation_view_login.setScale(0.5f)


        KeyboardVisibilityEvent.setEventListener(
                this
        ) { isOpen ->
            // some code depending on keyboard visiblity status
            if (isOpen) {

                val h: Int = detail_view.getHeight()
                val animation: Animation = TranslateAnimation(0f, 0f, 0f, -detail_view.getHeight().toFloat())
                animation.duration = 500
                animation.fillAfter = true
                animation.isFillEnabled = false
                animation.interpolator = BounceInterpolator()
                animation_view_login.setSpeed(1f)
                animation_view_login.playAnimation()
                guideline.animate().translationY(-detail_view.getHeight().toFloat())
                guideline.setGuidelinePercent(0.1f)
                toolbar_layout_login_username.animate().translationY(0f)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        detail_view.clearAnimation()

                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
            } else {
                val animation: Animation = TranslateAnimation(0f, 0f, -detail_view.getHeight().toFloat(), 0f)
                animation.duration = 500
                animation.fillAfter = true
                animation_view_login.setSpeed(-1f)
                animation_view_login.playAnimation()
                toolbar_layout_login_username.animate().translationY(-toolbar_layout_login_username.getHeight().toFloat())
                guideline.animate().translationY(0f)
                guideline.setGuidelinePercent(0.5f)

            }
        }


    }


}
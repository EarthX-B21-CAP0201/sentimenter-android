package com.earthx.sentimenter.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.entity.OnboardingItem
import com.earthx.sentimenter.databinding.ActivityOnboardingBinding
import com.earthx.sentimenter.view.authentication.signin.SigninActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var onboardingBinding: ActivityOnboardingBinding
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(onboardingBinding.root)
        setOnboardingItems()
        setupIndicator()
        setCurrentIndicator(0)
        onboardingBinding.buttonSignin.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
        }

    }

    private fun setOnboardingItems(){
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.featureone,
                    title = "Top 10 Chart",
                    description = "Lorem Ipsum Sir Dolor Amet"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.featuretwo,
                    title = "Sentiment Analysis",
                    description = "Lorem Ipsum Sir Dolor Amet"
                )
        ))
       onboardingBinding.onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingBinding.onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        }
        )
        (onboardingBinding.onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


    }
    private fun setupIndicator(){
        indicatorsContainer = onboardingBinding.indicatorContainer
        val indicator = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicator.indices){
            indicator[i] = ImageView(applicationContext)
            indicator[i]?.let{
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams= layoutParams
                indicatorsContainer.addView(it)
            }

        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i==position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )

            }
        }



    }


}
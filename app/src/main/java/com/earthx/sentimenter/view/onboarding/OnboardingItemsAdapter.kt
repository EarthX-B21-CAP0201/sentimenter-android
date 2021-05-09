package com.earthx.sentimenter.view.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.earthx.sentimenter.data.entity.OnboardingItem
import com.earthx.sentimenter.databinding.OnboardingItemContainerBinding

class OnboardingItemsAdapter(private val onBoardingItems: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>(){


    inner class OnboardingItemViewHolder(private val binding: OnboardingItemContainerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(onboardingItem: OnboardingItem) {
            with(binding) {
                imageOnboarding.setImageResource(onboardingItem.onboardingImage)
                textTitle.text = onboardingItem.title
                textDescription.text = onboardingItem.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val itemsOnboardingBinding = OnboardingItemContainerBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
        return OnboardingItemViewHolder(itemsOnboardingBinding)
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
       holder.bind(onBoardingItems[position])
    }


}

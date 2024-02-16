package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding==null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
        setTextToTextView()
        setSmile()
    }

    private fun setTextToTextView() {
        binding.tvRequiredAnswers.text = String.format(
            getString(R.string.required_answers),
            gameResult.gameSettings.minCountOfRightAnswers
        )
        binding.tvScoreAnswers.text = String.format(
            getString(R.string.your_count),
            gameResult.countOfRightAnswers
        )
        binding.tvRequiredPercentage.text = String.format(
            getString(R.string.required_percent),
            gameResult.gameSettings.minPercentOfRightAnswers
        )
        binding.tvScorePercentage.text = String.format(
            getString(R.string.your_percent),
            (gameResult.countOfRightAnswers/gameResult.countOfQuestions.toDouble()*100).toInt()
        )
    }

    private fun setSmile(){
        if (gameResult.winner) {
            binding.imageHappy.setImageResource(R.drawable.smile_happy)
        } else {
            binding.imageHappy.setImageResource(R.drawable.smile_sad)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun parseParam() {
        requireArguments().getParcelable(GAME_RESULT, GameResult::class.java)?.let {
            gameResult = it
        }
    }

    companion object {
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT, gameResult)
                }
            }
        }

        private const val GAME_RESULT = "gameResult"
        const val NAME = "GameFinishedFragment"
    }

}
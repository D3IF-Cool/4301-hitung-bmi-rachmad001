package org.d3if4025.hitungbmi.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if4025.hitungbmi.R
import org.d3if4025.hitungbmi.data.KategoriBmi
import org.d3if4025.hitungbmi.databinding.FragmentSaranBinding

class SaranFragment : Fragment() {

    private val args: SaranFragmentArgs by navArgs()
    private lateinit var binding: FragmentSaranBinding
    private  lateinit var kategoriBmi: KategoriBmi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        var kategori = arguments?.getString("kategori")

        when {
            kategori == "gemuk" -> kategoriBmi = KategoriBmi.GEMUK
            kategori == "kurus" -> kategoriBmi = KategoriBmi.KURUS
            else -> kategoriBmi = KategoriBmi.IDEAL
        }
        updateUI(kategoriBmi)
        binding.bmiData.text = "BMI : " + arguments?.getFloat("bmi").toString()
        binding.kategoriData.text = "Kategori : " + arguments?.getString("status")
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.saran_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shareSaran) {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, binding.textView.text)
            if (shareIntent.resolveActivity(
                            requireActivity().packageManager) != null) {
                startActivity(shareIntent)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun updateUI(kategori: KategoriBmi){
        val actionBar =  (requireActivity() as AppCompatActivity)
                .supportActionBar
        when (kategori) {
                KategoriBmi.KURUS -> {
                    actionBar?.title = getString(R.string.judul_kurus)
                    binding.imageView.setImageResource(R.drawable.kurus)
                    binding.textView.text = getString(R.string.saran_kurus)
                }
                KategoriBmi.IDEAL -> {
                    actionBar?.title = getString(R.string.judul_ideal)
                    binding.imageView.setImageResource(R.drawable.ideal)
                    binding.textView.text = getString(R.string.saran_ideal)
                }
                KategoriBmi.GEMUK -> {
                    actionBar?.title = getString(R.string.judul_gemuk)
                    binding.imageView.setImageResource(R.drawable.gemuk)
                    binding.textView.text = getString(R.string.saran_gemuk)
                }
        }
    }
}
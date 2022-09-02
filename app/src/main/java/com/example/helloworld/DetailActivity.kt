package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.database.Diary
import com.example.helloworld.database.DiaryDb
import com.example.helloworld.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {      //untuk meminimalisir salah ketik
        const val KEY_DIARY_ID = "diaryId"
    }

    private lateinit var binding: ActivityDetailBinding
    private var selectedDiary: Diary? = null            //karena diary nya null maka

    private val viewModel: DetailViewModel by lazy {
        val db = DiaryDb.getInstance(this)
        val factory = DetailViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra(KEY_DIARY_ID)) { //kalau key diary ada, maka true dan di edit mode posisinya
            supportActionBar?.title = getString(R.string.ubah_diary)
            val diaryId = intent.getIntExtra(KEY_DIARY_ID, 0)
            viewModel.getDiary(diaryId).observe(this) {
                selectedDiary = it          //jika di mode edit diary di isi dengan it, it itu akan menandakan data akan di isi dari database
                updateUI(it)
            }
        } else {
            supportActionBar?.title = getString(R.string.tambah_activity)
        }
    }

    private fun updateUI(diary: Diary) {
        binding.judulEditText.setText(diary.judul)
        binding.diaryEditText.setText(diary.diary)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { //hanya menampilkan menu saja
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuSimpan) {
//            finish()        //untuk kembali ke home atau main activity
            simpanDiary()
            return true
        }
        return false
    }

    private fun simpanDiary() {
        val judul = binding.judulEditText.text.toString()
        if (TextUtils.isEmpty(judul)) {
            Toast.makeText(this, getString(R.string.judul_harus_diisi), Toast.LENGTH_LONG).show()
            return
        }

        val diary = binding.diaryEditText.text.toString()
        if (TextUtils.isEmpty(diary)) {
            Toast.makeText(this, getString(R.string.diary_harus_diisi), Toast.LENGTH_LONG).show()
            return
        }
        if (selectedDiary == null) {
            val data = Diary(judul = judul, diary = diary)
            viewModel.insertDiary(data)
        } else {
            selectedDiary?.let {
                it.judul = judul
                it.diary = diary
                viewModel.updateDiary(it)
            }
        }
        finish()
    }
}
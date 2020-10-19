package sch6393.app.wavelength.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Optically Improved Mitochondrial Function Redeems Aged Human Visual Decline" +
                "\nhttps://academic.oup.com/biomedgerontology/article/75/9/e49/5863431\n\n"
    }
    val text: LiveData<String> = _text

    private val _text2 = MutableLiveData<String>().apply {
        value = "붉은 빛을 내는 긴 파장(670nm)을 눈에 쐬면 원추세포와 간상세포의 활성화에 도움을 줄 수 있어요." +
                "\n평소 명암 구분과 색 구분, 특히 파란색 구분이 잘 안된다면 한 번 붉은 빛을 눈에 쐬보세요." +
                "\n(논문에는 매일 아침 3분간 쐬도록 되어있어요.)\n\n"
    }
    val text2: LiveData<String> = _text2

    private val _text3 = MutableLiveData<String>().apply {
        value = "당연하지만 의료계 검증이 나오기 전까지는 조심하세요.\n\n"
    }
    val text3: LiveData<String> = _text3
}
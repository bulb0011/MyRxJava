package www.android.com.myrxjava.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import www.android.com.myrxjava.bean.Rben;

/**
 * Created by sunjays on 2018/10/22.
 */

public interface ApiService {


    @POST("/nyxt/ImplementAnalysis/selectCabin")
    Observable<List<Rben>> getData();
}

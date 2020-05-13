package it.univpm.mobile_programming_project.utils.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.HashMap;
import java.util.Map;

public class FirebaseFunctionsHelper {

    private FirebaseFunctions mFunctions;

    public FirebaseFunctionsHelper()
    {
        this.mFunctions = FirebaseFunctions.getInstance();
    }

    public Task< Map<String, Object> > getUserInfo() {

        // Call the function and extract the result
        // exports.getUserInfo

        return this.mFunctions
            .getHttpsCallable("getUserInfo")
            .call( )
            .continueWith(new Continuation<HttpsCallableResult, Map<String, Object>>() {
                @Override
                public Map<String, Object> then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                    HttpsCallableResult result = task.getResult();
                    Map<String, Object> resultData = (Map<String, Object>) result.getData();
                    return resultData;
                }
            });

    }


}

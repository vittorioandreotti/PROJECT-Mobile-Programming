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

    public Task< Boolean > isUserInitialized() {

        // Call the function and extract the result
        // exports.getUserInfo

        return this.mFunctions
            .getHttpsCallable("isUserInitialized")
            .call( )
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                @Override
                public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                    HttpsCallableResult result = task.getResult();
                    Boolean resultData = (Boolean) result.getData();
                    return resultData;
                }
            });

    }


}

using System;
using System.Collections.Generic;
using System.Text;

namespace XamarinApp.Models.Helpers
{
    class CloudFunctionResponse
    {
        bool HasError;
        long ErrorStatus;
        string ErrorMessage = "";

        public Dictionary<string, object> OriginalData;

        public Dictionary<string, object> Data;

        public CloudFunctionResponse(Dictionary<string, object> RispostaCloudFunction)
        {
            if( RispostaCloudFunction.ContainsKey("error") )
            {
                object errorObj;

                Dictionary<string, object> StatusCloudFunction = (Dictionary<string, object>)RispostaCloudFunction["error"];

                this.HasError = true;

                StatusCloudFunction.TryGetValue("code", out errorObj);
                this.ErrorStatus = (long)errorObj;

                StatusCloudFunction.TryGetValue("status", out errorObj);
                this.ErrorMessage = (string)errorObj;
            }
            else
            {
                this.Data = (Dictionary<string, object>)RispostaCloudFunction["data"];
                this.HasError = true;
            }

        }


    }
}

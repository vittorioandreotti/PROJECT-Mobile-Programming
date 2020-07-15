using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Text;

namespace XamarinApp.Models.Helpers
{
    public class CloudFunctionResponse
    {
        public bool HasError;
        public long ErrorStatus;
        public string ErrorMessage = "";

        public Dictionary<string, object> OriginalData;

        public JObject Data;

        public CloudFunctionResponse(Dictionary<string, object> RispostaCloudFunction)
        {
            if( RispostaCloudFunction.ContainsKey("error") )
            {
                object errorObj;

                JObject StatusCloudFunction = (JObject)RispostaCloudFunction["error"];

                this.HasError = true;

                errorObj = StatusCloudFunction["code"];
                this.ErrorStatus = (long)errorObj;

                errorObj = StatusCloudFunction["status"];
                this.ErrorMessage = (string)errorObj;
            }
            else
            {
                this.Data = (JObject)RispostaCloudFunction["result"];
                this.HasError = false;
            }

        }


    }
}

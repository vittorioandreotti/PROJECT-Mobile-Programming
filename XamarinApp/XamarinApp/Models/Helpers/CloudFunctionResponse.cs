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

        public bool BooleanData;
        public JObject JsonData;

        public object ObjectData;

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
                this.ObjectData = RispostaCloudFunction["result"];

                if(this.ObjectData is JObject )
                {
                    this.JsonData = (JObject)this.ObjectData;
                }else if (this.ObjectData is bool )
                {
                    this.BooleanData = (bool)this.ObjectData;
                }

                this.HasError = false;
            }

        }

    }
}

using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Text;

namespace XamarinApp.Models.Helpers
{
    public abstract class JsonHelper
    {
        public static bool IsJsonTokenNull(JToken token)
        {
            return token.Type is JTokenType.Null;
        }
    }
}

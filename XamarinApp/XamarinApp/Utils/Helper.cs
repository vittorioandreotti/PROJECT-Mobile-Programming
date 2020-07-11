using Android.Annotation;
using Android.Icu.Text;
using Android.Net;
using Java.Util;
using System;
using System.Collections.Generic;
using Date = Java.Util.Date;
using System.Text;
using System.Text.RegularExpressions;

namespace XamarinApp.Utils
{
    abstract class Helper
    {
        private static string VALID_EMAIL_ADDRESS_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        public static Boolean IsEmailValid(String EmailStr)
        {
            Regex regex = new Regex(VALID_EMAIL_ADDRESS_REGEX);
            Match match = regex.Match(EmailStr);
            return match.Success;
        }

        public static Date FromStringToDate(String DateString)
        {
            return FromStringToDate(DateString, "dd/MM/yyyy");
        }

        public static Date FromStringToDate(String DateString, String DateFormat)
        {
            try
            {
                SimpleDateFormat DataFormat = new SimpleDateFormat(DateFormat);
                return DataFormat.Parse(DateString);
            }
            catch (ParseException e)
            {
                e.PrintStackTrace();
                return null;
            }
        }

        public static Date FromStringToDateTime(String DateString, String OraString)
        {
            return FromStringToDate(DateString + " " + OraString, "dd/MM/yyyy HH:mm");
        }

        public static String FormatDateToStringWithHour(Date Data)
        {
            if (Data == null) return "-";
            return new SimpleDateFormat("dd/MM/yyyy HH:mm").Format(Data);
        }

        public static String FormatDateToString(Date Data)
        {
            if (Data == null) return "-";
            return new SimpleDateFormat("dd/MM/yyyy").Format(Data);
        }

        public static String GetDateFormat()
        {
            return "%02d/%02d/%04d";
        }

        public static String GetTimeFormat()
        {
            return "%02d:%02d";
        }

        public static Date FromMillisToDate(Int64 DataInserimento)
        {
            // if (DataInserimento == null) return null;
            return new Date(DataInserimento);
        }
    }
}

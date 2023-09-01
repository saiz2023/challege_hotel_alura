package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class conversionDeTipo {
	
	 public Object convertir(String valor) {
	        // Intentar convertir a Long
	        try {
	            return Long.parseLong(valor);
	        } catch (NumberFormatException e) {
	            // No es Long
	        }

	        // Intentar convertir a Date
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date date = dateFormat.parse(valor);
	            return date;
	        } catch (ParseException e) {
	            // No es Date
	        }

	        // Intentar convertir a Double
	        try {
	            return Double.parseDouble(valor);
	        } catch (NumberFormatException e) {
	            // No es Double
	        }

	        // Mantener como String
	        return valor;
	    }
	 
		 
		    public static boolean isLong(String value) {
		        try {
		            Long.parseLong(value);
		            return true;
		        } catch (NumberFormatException e) {
		            return false;
		        }
		    }

		    public static boolean isDouble(String value) {
		        try {
		            Double.parseDouble(value);
		            return true;
		        } catch (NumberFormatException e) {
		            return false;
		        }
		    }

		    public static boolean isDate(String value) {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Cambia el formato si es necesario
		        try {
		            dateFormat.parse(value);
		            return true;
		        } catch (ParseException e) {
		            return false;
		        }
		    }

		    public static Date convertStringToDate(String dateString, String format) {
		        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		        try {
		            return dateFormat.parse(dateString);
		        } catch (ParseException e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		    public long convertToLong(Object value) {
		        if (value instanceof Long) {
		            return (long) value;
		        } else if (value instanceof String) {
		            String strValue = (String) value;
		            if (isLong(strValue)) {
		                return Long.parseLong(strValue);
		            }
		        }
		       return 0;
		    }
		    public Date convertToDate(Object value) {
		        if (value instanceof Date) {
		            return (Date) value;
		        } else if (value instanceof String) {
		            String strValue = (String) value;
		            if (isDate(strValue)) {
		                return convertStringToDate(strValue, "yyyy-MM-dd"); // Cambia el formato si es necesario
		            }
		        }
		        throw new IllegalArgumentException("No se puede convertir a tipo Date");
		    }
		    public double convertToDouble(Object value) {
		        if (value instanceof Double) {
		            return (double) value;
		        } else if (value instanceof String) {
		            String strValue = (String) value;
		            if (isDouble(strValue)) {
		                return Double.parseDouble(strValue);
		            }
		        }
		        throw new IllegalArgumentException("No se puede convertir a tipo double");
		    }
		    public String convertToString(Object value) {
		        if (value instanceof String) {
		            return (String) value;
		        } else {
		            return String.valueOf(value);
		        }
		    }



}

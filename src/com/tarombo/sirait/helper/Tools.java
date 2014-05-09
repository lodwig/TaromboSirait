package com.tarombo.sirait.helper;



public class Tools {
	public static String FatherId = null;
	public static void setFatherId(String fatherId){
		FatherId = fatherId;
	}
	public static String getFatherId(){
		if (FatherId == null)
			FatherId = ConstansInterface.FatherIdFirst;
		return FatherId;
	}
	
//	public static int GetImage(int urutanGambar){
//		int result = ConstansInterface.MONUMENT_OMPU_RAJA_SIRAIT;
//		if (urutanGambar > 0){
//			if (urutanGambar == 1){
//				result = ConstansInterface.MONUMENT_OMPU_RAJA_SIRAIT;
//			}else if (urutanGambar == 2){
//				result = ConstansInterface.MONUMENT_RAJA_MARDOBUR;
//			}else if (urutanGambar == 3){
//				result = ConstansInterface.MONUMENT_TAMBAK_RAJA_TOGA_SIRAIT;
//			}
//		}
//		
//		return result;
//	}
	
//	public static int GetImageDesc(int urutanGambar){
//		int result = ConstansInterface.DESC_MONUMENT_OMPU_RAJA_SIRAIT;
//		if (urutanGambar > 0){
//			if (urutanGambar == 1){
//				result = ConstansInterface.DESC_MONUMENT_OMPU_RAJA_SIRAIT;
//			}else if (urutanGambar == 2){
//				result = ConstansInterface.DESC_MONUMENT_RAJA_MARDOBUR;
//			}else if (urutanGambar == 3){
//				result = ConstansInterface.DESC_MONUMENT_TAMBAK_RAJA_TOGA_SIRAIT;
//			}
//		}
//		
//		return result;
//	}
}

package com.ghoomo.ghoomo.ui.utility;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Surface;

import java.util.Random;


/**
 * Created by Agoel on 02-09-2016.
 */
public class Utility {


    private static final String TAG = "Utility";

    /**
     * s
     * to check whether camera hardware is there or not
     *
     * @param context
     * @return
     */
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * save image to external directory
     *
     * @param
     */
//    public static void saveImageToExternalStorage(Bitmap image, int tripnumber, String imgName) {
//
//        String username = GhoomoPreference.getInstance().optStringValue(GhoomoPreference.KEY_USERNAME, "");
//
////        image = scaleCenterCrop(image, 200, 200);
//        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + username + "/" + tripnumber;
//        try {
//            File dir = new File(fullPath);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            OutputStream fOut = null;
//            File file = new File(fullPath, imgName);
//
//            if (file.exists())
//                file.delete();
//
//            file.createNewFile();
//            fOut = new FileOutputStream(file);
//            // 100 means no compression, the lower you go, the stronger the compression
//            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//
//            fOut.flush();
//            fOut.close();
//        } catch (Exception e) {
//            Log.e("saveToExternalStorage()", e.getMessage());
//        }
//    }
    public static int setPhotoOrientation(Activity activity, int cameraId) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        // do something for phones running an SDK before lollipop
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }

        return result;
    }

    /**
     * get fdriver image from device folder
     *
     * @return
     */
//    public static Bitmap getDriverImageFromSDCard(int tripNumber) {
//
//        String username = GhoomoPreference.getInstance().optStringValue(GhoomoPreference.KEY_USERNAME, "");
//        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + username + "/" + tripNumber;
//
//        File f = new File(fullPath + "/driver_photo");
//        Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
//
//        return bmp;
//    }

    /**
     * decreasing the size of image
     *
     * @return
     */
//    public static Bitmap getDecreasedImageFromSDCard(int tripNumber, String name, int width, int height) {
//
//        String username = GhoomoPreference.getInstance().optStringValue(GhoomoPreference.KEY_USERNAME, "");
//        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + username + "/" + tripNumber;
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//
//// Set height and width in options, does not return an image and no resource taken
//
//        int pow = 2;
//        while (options.outHeight >> pow > (height / 2) || options.outWidth >> pow > width)
//            pow += 1;
//        options.inSampleSize = 1 << pow;
//        options.inJustDecodeBounds = false;
//        Bitmap bmp = BitmapFactory.decodeFile(fullPath + "/" + name, options);
//
//        return bmp;
//    }

    /*
  * Making image in circular shape
  */
//    public static Bitmap getRoundedShape(Context context, Bitmap scaleBitmapImage) {
//        // TODO Auto-generated method stub
//        int targetWidth = 90;
//        int targetHeight = 90;
//        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
//                targetHeight, Bitmap.Config.ARGB_8888);
//
//        Canvas canvas = new Canvas(targetBitmap);
//        Path path = new Path();
//        path.addCircle(((float) targetWidth - 1) / 2,
//                ((float) targetHeight - 1) / 2,
//                (Math.min(((float) targetWidth),
//                        ((float) targetHeight)) / 2),
//                Path.Direction.CCW);
//
//        canvas.clipPath(path);
//        Bitmap sourceBitmap;
//        if (scaleBitmapImage != null) {
//            sourceBitmap = scaleBitmapImage;
//        } else {
//            sourceBitmap = BitmapFactory.decodeResource(context.getResources(),
//                    R.drawable.driver_img);
//        }
//
//        canvas.drawBitmap(sourceBitmap,
//                new Rect(0, 0, sourceBitmap.getWidth(),
//                        sourceBitmap.getHeight()),
//                new Rect(0, 0, targetWidth,
//                        targetHeight), null);
//        return targetBitmap;
//    }

    /**
     * getting size for display phone and tablet
     *
     * @param display
     * @return
     */
//    public static Point getDisplaySize(Display display) {
//        Point size = new Point();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            display.getSize(size);
//        } else {
//            int width = display.getWidth();
//            int height = display.getHeight();
//            size = new Point(width, height);
//        }
//        return size;
//    }

    /**
     * delete particular folder for particular trip number
     *
     * @param tripnumber
     */
//    public static void deleteTripFolder(int tripnumber) {
//        String username = GhoomoPreference.getInstance().optStringValue(GhoomoPreference.KEY_USERNAME, "");
//        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + username + "/" + tripnumber);
//
//        if (dir.isDirectory()) {
//            String[] children = dir.list();
//            for (int i = 0; i < children.length; i++) {
//                new File(dir, children[i]).delete();
//            }
//        }
//    }

    /**
     * write external storage permission
     *
     * @return
     */
//    public static boolean getWriteStoragPermission(Activity context) {
//        if (ContextCompat.checkSelfPermission(context,
//                Manifest.permission.INTERNET)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(context,
//                    new String[]{Manifest.permission.INTERNET},
//                    GhoomoConstant.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//            return false;
//        } else
//            return true;
//    }

    /**
     * @param blobByteArray
     * @return
     */
    public static Bitmap convertBlobToBitmap(byte[] blobByteArray) {
        Bitmap tempBitmap = null;
        if (blobByteArray != null)
            tempBitmap = BitmapFactory.decodeByteArray(blobByteArray, 0, blobByteArray.length);
        return tempBitmap;
    }

    //method to parse incoming api request data

//    public static InputApiData parseJson(Context context, String jsonString) {
//
//        InputApiData incomingdata = new InputApiData();
//        HeaderData headerData = new HeaderData();
//        ItemData itemData = new ItemData();
//
//        try {
//            JSONObject jsonObj = new JSONObject(jsonString);
//
//            JSONObject dJsonObject = jsonObj.optJSONObject("d");
//            JSONArray resultJsonArray = dJsonObject.optJSONArray("results");
//
//            for (int i = 0; i < resultJsonArray.length(); i++) {
//
//                JSONObject jsonObject = resultJsonArray.getJSONObject(i);
//
//                JSONObject obj = jsonObject.optJSONObject("ZticlMobileDisplay");
////                String evMsg = obj.optString("EvMessage");
////                incomingdata.setEvMsg(evMsg);
////                Log.d(TAG, "evMsg" + evMsg);
////
//                String iv = jsonObject.getString("IvPartner");
//                String dealerCode = jsonObject.optString("EvDealer");
//                String dealerName = jsonObject.optString("EvDealerName");
//                String cityCode = jsonObject.optString("EvCityCode");
//                String cityName = jsonObject.optString("EvCityName");
//
//                JSONObject jHeaderObj = jsonObject.optJSONObject("header");
//                JSONArray resultTripJjsonArray = jHeaderObj.optJSONArray("results");
//
//                List<TripDetails> tripDetailsList = new ArrayList<>();
//
//                for (int j = 0; j < resultTripJjsonArray.length(); j++) {
//
//                    TripDetails trip = new TripDetails();
//                    JSONObject tripObj = resultTripJjsonArray.optJSONObject(j);
//                    trip.setTripNumber(tripObj.optInt("TripNumber"));
//                    trip.setDocumentNo(tripObj.optString("DocumentNo"));
//                    trip.setPlantName(tripObj.optString("PlantText"));
//                    trip.setPlantCode(tripObj.optString("PlantCode"));
//                    trip.setVehicleNumber(tripObj.optString("TruckNo"));
//                    trip.setLoadingDate(tripObj.optString("LoadingDate"));
//                    trip.setTranspoterName(tripObj.optString("TransporterName"));
//                    trip.setTranspoterCode(tripObj.optString("TranspoterCode"));
//                    trip.setDealerCode(tripObj.optString("DealerCode"));
//                    trip.setDealerName(tripObj.optString("DealerName"));
//                    trip.setExpectedDate(tripObj.optString("ExpectedDate"));
//                    trip.setDelay(tripObj.optString("Delay"));
//                    trip.setSummaryVariantsCount(tripObj.optString("CountVariant"));
//                    trip.setSummaryVEhiclesCount(tripObj.optString("CountVechiles"));
//                    trip.setSummaryInvoicesCount(tripObj.optString("CountInvoices"));
//                    trip.setCountRowsLowerDeck(tripObj.optString("CountRowsLowerDeck"));
//                    trip.setCountRowUpperDeck(tripObj.optString("CountRowUpperDeck"));
//                    trip.setCountColumnLowerDeck(tripObj.optString("CountCollumLowerDeck"));
//                    trip.setCountColumnUpperDeck(tripObj.optString("CountCollumUpperDeck"));
//                    trip.setIsReviewDone("no");
//
//                    tripDetailsList.add(trip);
//                }
//
//                headerData.setTripsList(tripDetailsList);
//
//                JSONObject jItemsObj = jsonObject.optJSONObject("items");
//                JSONArray resultChassisJsonArray = jItemsObj.optJSONArray("results");
//
//                List<BikeChassis> bikeChassisList = new ArrayList<>();
//                for (int k = 0; k < resultChassisJsonArray.length(); k++) {
//
//                    BikeChassis chasisItem = new BikeChassis();
//                    Log.d(TAG, " getting chasis item " + k);
//                    JSONObject chasisObj = resultChassisJsonArray.optJSONObject(k);
//
//                    chasisItem.setTripNumber(chasisObj.optString("TripNumber"));
////                    chasisItem.setBikeName(chasisObj.optString("bikeName"));
////                    chasisItem.setBikeType(chasisObj.optString("bikeType"));
//                    chasisItem.setDocumentNo(chasisObj.optString("DocumentNo"));
//                    chasisItem.setMaterialNo(chasisObj.optString("MaterialNo"));
//                    chasisItem.setMaterialText(chasisObj.optString("MaterialText"));
//                    chasisItem.setChassisNumber(chasisObj.optString("ChasisNo"));
//                    String variantCode = chasisObj.optString("VariantCode");
//                    if (chasisObj.optString("VariantCode").length() > 12) {
//                        variantCode = variantCode.substring(0, 12);
//                    }
//                    if (GhoomoConstant.showLog) {
//                        System.out.println(" Variant Code :" + variantCode);
//                    }
//                    chasisItem.setVariantCode(variantCode);
////                    chasisItem.setVariantCode(chasisObj.optString("VariantCode"));
//                    chasisItem.setVariantName(chasisObj.optString("VariantName"));
//                    bikeChassisList.add(chasisItem);
//                }
//
//                itemData.setBikeChassisList(bikeChassisList);
//
//                incomingdata.setIvPartner(iv);//EvCityCode();
//                incomingdata.setEvDealer(dealerCode);
//                incomingdata.setEvDealerName(dealerName);//
//                incomingdata.setEvCityCode(cityCode);//EvDealerName(dealerName);//
//                incomingdata.setEvCityName(cityName);
//                incomingdata.setHeader(headerData);
//                incomingdata.setItems(itemData);
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.d(TAG, "error" + e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return incomingdata;
//    }

//    public static String constructUploadJson(Context context, UploadTripData tripData) {
//        Gson gson = new Gson();
//        Log.d(TAG, "constructed Json" + gson.toJson(tripData));
//
//        return gson.toJson(tripData);
//    }

//    public static String constructUploadImageJson(Context context, UploadImagesData imageData) {
//        Gson gson = new Gson();
//        Log.d(TAG, "constructed Json" + gson.toJson(imageData));
//
//        return gson.toJson(imageData);
//    }

    public static String getRegisterRequestBody() {
        String registerBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<entry xmlns=\"http://www.w3.org/2005/Atom\" xmlns:m=\"http://schemas.microsoft.com/ado/2007/08/dataservices/metadata\" xmlns:d=\"http://schemas.microsoft.com/ado/2007/08/dataservices\">\n" +
                "<content type=\"application/xml\">\n" +
                "<m:properties>\n" +
                "<d:DeviceType>Android</d:DeviceType>\n" +
                "</m:properties>\n" +
                "</content>\n" +
                "</entry>\n";

        return registerBody;
    }

//    public static UploadTripData addDummyData(UploadTripData uploadTripData) {
//        UploadTripData tripData = new UploadTripData();
//
//        List<Shortage> shortageList = new ArrayList<Shortage>();
//        List<Damage> damageList = new ArrayList<Damage>();
//        List<ChasisDetails> chasisList = new ArrayList<ChasisDetails>();
//
//        Trip trip = new Trip();
//        Review review = new Review();
//
//        trip.setDriverName(uploadTripData.getTripInfo().getDriverName());
//        trip.setAccidentFlag(uploadTripData.getTripInfo().isAccidentFlag());
//        trip.setChasisReviewFlag(uploadTripData.getTripInfo().isChasisReviewFlag());
//        trip.setDealerNo(uploadTripData.getTripInfo().getDealerNo());
//        trip.setDelayDays(uploadTripData.getTripInfo().getDelayDays());
//        trip.setDelayReason(uploadTripData.getTripInfo().getDelayReason());
//        trip.setDocumentNo(uploadTripData.getTripInfo().getDocumentNo());
//        trip.setRecievedFlag(uploadTripData.getTripInfo().isRecievedFlag());
//        trip.setReportingDate(uploadTripData.getTripInfo().getReportingDate());
//        trip.setReportingTime(uploadTripData.getTripInfo().getReportingTime());
//        trip.setSignatureFlag("");
//        trip.setSignatureImage(uploadTripData.getTripInfo().getSignatureImage());
//        trip.setUnloadingDate(uploadTripData.getTripInfo().getUnloadingDate());
//        trip.setUnloadingTime(uploadTripData.getTripInfo().getUnloadingTime());
//        trip.setTripNumber(uploadTripData.getTripInfo().getTripNumber());
//
//        for (int i = 0; i < uploadTripData.getReviewInfo().getChassis_list().size(); i++) {
//            ChasisDetails cd = uploadTripData.getReviewInfo().getChassis_list().get(i);
//            cd.setChasisNo(cd.getChasisNo());
//            cd.setVariantCode(cd.getVariantCode());
//            cd.setVariantName(cd.getVariantName());
//
//            for (int j = 0; i < cd.getDamage_list().size(); j++) {
//                Damage damage = cd.getDamage_list().get(j);
//                damage.setType(damage.getDamageType());
//                damage.setDamageId(damage.getDamageId());
//                damage.setReason("Accident");
//                damage.setDamagePhoto(null);
//                damage.setPhoto_name(damage.getPhoto_name());
//                damageList.add(damage);
//            }
//
//            for (int k = 0; i < cd.getShoratge_list().size(); k++) {
//                Shortage shortage = cd.getShoratge_list().get(k);
//                shortage.setPhoto_name(shortage.getPhoto_name());
//                shortage.setReason(shortage.getReason());
//                shortage.setShortageId(shortage.getShortageId());
//                shortage.setType("NoParts");
//                shortage.setShortagePhoto(null);
//                shortageList.add(shortage);
//            }
//
//            cd.setDamage_list(damageList);
//            cd.setShoratge_list(shortageList);
//            chasisList.add(cd);
//        }
//
//        review.setChassis_list(chasisList);
//
//        tripData.setTripInfo(trip);
//        tripData.setReviewInfo(review);
//
//        return tripData;
//
//    }

    /**
     * Verify that network connection is available before making a request.
     */
    public static boolean checkNetworkConnection(Context context) {
        boolean isNetworkAvailable = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifi != null && wifi.isConnected()) {
                isNetworkAvailable = true;
            } else {
                NetworkInfo mobileData = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mobileData != null && mobileData.isConnected()) {
                    isNetworkAvailable = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetworkAvailable;
    }

    /**
     * @return
     */
    public static int randInt() {
        Random rand = new Random();
        int randomNum = rand.nextInt(999);
        return randomNum;
    }


    public static void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

//    public static Camera getCameraInstance() {
//        Camera c = null;
//
//        try {
//            //attempt to get a Camera instance
//            c = Camera.open();
//        } catch (Exception e) {
//            //Camera is not available(in use or does not exist)
//        }
//        return c;//returns null if camera is unavailable
//    }


}

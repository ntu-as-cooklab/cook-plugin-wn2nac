package tw.edu.ntu.as.cook;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.widget.Toast;
import java.lang.Runnable;
import android.os.Looper;
import android.os.Handler;
import java.util.Observable;
import java.util.Observer;
import ch.skywatch.windoo.api.JDCWindooEvent;
import ch.skywatch.windoo.api.JDCWindooManager;

public class WN2NAC extends CordovaPlugin implements Observer
{
    private static Context context;
    private static WN2NAC instance = new WN2NAC(); // Singleton instance
    private static JDCWindooManager jdcWindooManager;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException
    {
        if (action.equals("start"))
        {
            context = this.cordova.getActivity().getApplicationContext();
            jdcWindooManager = JDCWindooManager.getInstance();
            jdcWindooManager.setToken("4a38879a-ff50-4103-8f44-ab9a84b72c21");
            jdcWindooManager.addObserver(instance);
            jdcWindooManager.enable(context);
            callbackContext.success("Windoo manager started.");
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void update(Observable observable, final Object object)
    {
        final JDCWindooEvent windooEvent = (JDCWindooEvent) object;
        switch(windooEvent.getType())
        {
            case JDCWindooEvent.JDCWindooAvailable:
                Toast.makeText(context, "Windoo available", Toast.LENGTH_SHORT).show();
                break;
            case JDCWindooEvent.JDCWindooNotAvailable:
                //Toast.makeText(context, "Available", Toast.LENGTH_SHORT).show();
                break;
            case JDCWindooEvent.JDCWindooCalibrated:
                //Toast.makeText(context, "Available", Toast.LENGTH_SHORT).show();
                break;
            case JDCWindooEvent.JDCWindooPublishSuccess:
                //Toast.makeText(context, "Available", Toast.LENGTH_SHORT).show();
                break;
            case JDCWindooEvent.JDCWindooPublishException:
                //Toast.makeText(context, "Available", Toast.LENGTH_SHORT).show();
                break;
            case JDCWindooEvent.JDCWindooVolumeNotAtItsMaximum:
                //Toast.makeText(context, "Available", Toast.LENGTH_SHORT).show();
                break;
            case JDCWindooEvent.JDCWindooNewWindValue :
                new Handler(context.getMainLooper()).post(new Runnable()
                    {
                        @Override
                        public void run() { Toast.makeText(context, "Wind speed: "+ String.valueOf(windooEvent.getData()), Toast.LENGTH_SHORT).show(); }
                    }
                );
                //Toast.makeText(context, "Wind speed: "+ String.valueOf(windooEvent.getData()), Toast.LENGTH_SHORT).show();
                //addWind((Double) windooEvent.getData());
                //bus.post(new WnService.DebugEvent("Wind: "+ String.valueOf(windooEvent.getData())));
                break;
            case JDCWindooEvent.JDCWindooNewTemperatureValue :
                //Toast.makeText(context, "Temperature: "+ String.valueOf(windooEvent.getData()), Toast.LENGTH_SHORT).show();
                //addTemperature((Double) windooEvent.getData());
                //bus.post(new WnService.DebugEvent("Temperature: " + String.valueOf(windooEvent.getData())));
                break;
            case JDCWindooEvent.JDCWindooNewHumidityValue :
                //Toast.makeText(context, "Humidity: "+ String.valueOf(windooEvent.getData()), Toast.LENGTH_SHORT).show();
                //addHumidity((Double) windooEvent.getData());
                //bus.post(new WnService.DebugEvent("Humidity: " + String.valueOf(windooEvent.getData())));
                break;
            case JDCWindooEvent.JDCWindooNewPressureValue :
                //Toast.makeText(context, "Pressure: "+ String.valueOf(windooEvent.getData()), Toast.LENGTH_SHORT).show();
                //addPressure((Double) windooEvent.getData());
                //bus.post(new WnService.DebugEvent("Pressure: " + String.valueOf(windooEvent.getData())));
                break;
            default:
        }
    }
}

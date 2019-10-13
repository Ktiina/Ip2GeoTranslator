package ipGeoClient;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;

import com.google.common.net.InetAddresses;



public class IpGeoClient {
	private static final String LICENSE_KEY_TEST = "0"; // 0 for testing, according to documentation
	
	public IpGeoClient() {}
	
	public List<String> ipToGeo(String ipString) {
		IP2Geo IP2GeoServ = new IP2Geo(); //created service object
		IP2GeoSoap IP2GeoServSoap = IP2GeoServ.getIP2GeoSoap(); //create SOAP object (a port of the service)
		
		List<String> returner = new ArrayList<>();
		
		InetAddress ipAddr = InetAddresses.forString(ipString);
		Inet4Address ip4Addr = InetAddresses.getCoercedIPv4Address(ipAddr); // for IPv6 cases		
		
		// substring(1) needed for delete '/' from String representation of InetAddress "HOSTNAME/ADDRESS"
		IPInformation ipInfo = IP2GeoServSoap.resolveIP(ip4Addr.toString().substring(1), LICENSE_KEY_TEST);
		
		returner.add(ipInfo.getCountry());
		returner.add(ipInfo.getCountryCode());
		returner.add(ipInfo.getCity());
		returner.add(ipInfo.getStateProvince());
		returner.add(ipInfo.getAreaCode());
		returner.add(ipInfo.getRegionName());
		returner.add(ipInfo.getOrganization());
		returner.add(Double.toString(ipInfo.getLatitude()));
		returner.add(Double.toString(ipInfo.getLongitude()));
		returner.add(ipInfo.getTimeZone());
		returner.add(Double.toString(ipInfo.getCertainty()));
		
        return returner;
	}
	
	public Boolean checkIpValidity(String ipString)
	{
		if (!InetAddresses.isInetAddress(ipString))
		{
			return false;
		}
		
		return true;
	}

}

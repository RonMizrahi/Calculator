package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
 
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
 
public class HttpPostReq 
{

	private String restUrl="http://localhost:8080";
	 
    public HttpPostReq()
    {

    }
    
    public void setPostReuest(String postType) {
    	restUrl = "http://localhost:8080/" + postType;
   
    }
    
    public String sendPostRequest(String expr) {
       
        JSONObject user=new JSONObject();
		user.put("mathExpr", expr); 
        String jsonData=user.toString();
        HttpPostReq httpPostReq=new HttpPostReq();
        HttpPost httpPost=httpPostReq.createConnectivity(restUrl , "", "");
        return httpPostReq.executeReq( jsonData, httpPost);   	
    }
    
    private HttpPost createConnectivity(String restUrl, String username, String password)
    {
        HttpPost post = new HttpPost(restUrl);
        String auth=new StringBuffer(username).append(":").append(password).toString();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        post.setHeader("AUTHORIZATION", authHeader);
        post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setHeader("X-Stream" , "true");
        return post;
    }
     
    private String executeReq(String jsonData, HttpPost httpPost)
    {
        try{
            return executeHttpRequest(jsonData, httpPost);
        }
        catch (UnsupportedEncodingException e){
        	return "error while encoding api url : "+e;
        }
        catch (IOException e){
        	return "ioException occured while sending http request : "+e;
        }
        catch(Exception e){
        	return "exception occured while sending http request : "+e;
        }
        finally{
            httpPost.releaseConnection();
        }
    }
     
    private String executeHttpRequest(String jsonData,  HttpPost httpPost)  throws UnsupportedEncodingException, IOException
    {
        HttpResponse response=null;
        String line = "";
        StringBuffer result = new StringBuffer();
        httpPost.setEntity(new StringEntity(jsonData));
        HttpClient client = HttpClientBuilder.create().build();
        response = client.execute(httpPost);
        System.out.println("Post parameters : " + jsonData );
        System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        while ((line = reader.readLine()) != null){ result.append(line); }
        System.out.println(result.toString());
        return result.toString();
    }
}
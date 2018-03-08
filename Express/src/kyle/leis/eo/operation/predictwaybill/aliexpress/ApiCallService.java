package kyle.leis.eo.operation.predictwaybill.aliexpress;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * api���õķ�����
 */
public class ApiCallService {

    /**
     * ����api
     * @param urlHead �����url��openapi�Ĳ��֣���http://gw.open.1688.com/openapi/
     * @param urlPath protocol/version/namespace/name/appKey
     * @param appSecretKey ���Ե�app��Կ�����Ϊ�ձ�ʾ����Ҫǩ��
     * @param params api�������map�����api��Ҫ�û���Ȩ���ʣ���ô���������Ȩ���̣�params�б������access_token����
     * @return json��ʽ�ĵ��ý��
     */
    public static String callApi(String urlHead, String urlPath, String appSecretKey, Map<String, String> params){
        final HttpClient httpClient = new HttpClient();
        final PostMethod method = new PostMethod(urlHead + urlPath);
        method.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        
        if(params != null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                method.setParameter(entry.getKey(), entry.getValue());
            }
        }
        if(appSecretKey != null){
            method.setParameter("_aop_signature", CommonUtil.signatureWithParamsAndUrlPath(urlPath, params, appSecretKey));
        }
        String response = "";
        try{
            httpClient.executeMethod(method);
            response = CommonUtil.parserResponse(method);
        } catch (HttpException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally{
            method.releaseConnection();
        }
        return response;
    }
    
}
	
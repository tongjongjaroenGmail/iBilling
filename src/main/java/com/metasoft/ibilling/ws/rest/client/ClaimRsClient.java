package com.metasoft.ibilling.ws.rest.client;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.metasoft.ibilling.ws.bean.json.ClaimRs;
@Path("/WS_RPT_DATA.php")
public interface ClaimRsClient {
   @GET
   ClaimRs loadClaims();
}
 
/*
https://tools.ietf.org/html/rfc1808.html
  The URL syntax is dependent upon the scheme.  Some schemes use
  reserved characters like "?" and ";" to indicate special components,
  while others just consider them to be part of the path.  However,
  there is enough uniformity in the use of URLs to allow a parser to
  resolve relative URLs based upon a single, generic-RL syntax.  This
  generic-RL syntax consists of six components:
  <scheme>://<net_loc>/<path>;<params>?<query>#<fragment>
  
https://tools.ietf.org/html/rfc3986#section-3.4
  3.4.  Query
  
  The query component contains non-hierarchical data that, along with
  data in the path component (Section 3.3), serves to identify a
  resource within the scope of the URI's scheme and naming authority
  (if any).  The query component is indicated by the first question
  mark ("?") character and terminated by a number sign ("#") character
  or by the end of the URI.
*/

function get_query_string_parameters() {
    result = {};
    
    var uri = document.location.href;
    
    if (uri.indexOf("?") < 0)
        return result;
    query_string = uri.substring(uri.indexOf("?"));
    if (query_string.indexOf("#") >= 0)
        query_string = query_string.substring(0, query_string.indexOf("#"));

    
    query_pattern = /(?:[?&])([^&=]+)(?:=)([^&]*)/g;
    decode = function(str) {return decodeURIComponent(str.replace(/\+/g, " "));};
    while(match = query_pattern.exec(query_string))
        result[decode(match[1])] = decode(match[2]);
    return result;
}


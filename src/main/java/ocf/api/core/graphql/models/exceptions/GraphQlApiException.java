package ocf.api.core.graphql.models.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class GraphQlApiException extends RuntimeException implements GraphQLError {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> extension;

	public GraphQlApiException(String errorCode, String errorMessage, String errorKey, String apiId) {
		super();
		if (null == extension)
			extension = new HashMap();
		extension.put("errorCode", errorCode);
		extension.put("errorMessage", errorMessage);
		extension.put("errorKey", errorKey);
		extension.put("apiId", apiId);

	}

	public List<SourceLocation> getLocations() {
		return new ArrayList();

	}

	public ErrorClassification getErrorType() {
		return new ErrorClassification() {
		};
	}

	
	public Map<String, Object> getExtensions() {
		return this.extension;
	}
}

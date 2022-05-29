package ocf.api.core.graphql.exception;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import ocf.api.core.graphql.models.exceptions.GraphQlApiException;
import ocf.api.core.graphql.models.exceptions.GraphQlValidationException;

@DgsComponent
public class OCFDataFetchingExceptionHandler implements DataFetcherExceptionHandler {

	public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
		// TODO Auto-generated method stub
		DefaultDataFetcherExceptionHandler defaultDataFetcherExceptionHandler = new DefaultDataFetcherExceptionHandler();
		if (handlerParameters.getException() instanceof GraphQlApiException
				|| handlerParameters.getException() instanceof GraphQlValidationException) {
			return DataFetcherExceptionHandlerResult.newResult()
					.error((GraphQLError) handlerParameters.getException()).build();
		} else {
			return defaultDataFetcherExceptionHandler.onException(handlerParameters);
		}
	}

}

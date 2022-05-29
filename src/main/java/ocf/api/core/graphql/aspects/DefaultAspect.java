package ocf.api.core.graphql.aspects;

import java.text.MessageFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import graphql.execution.DataFetcherExceptionHandlerParameters;
import ocf.api.core.graphql.models.exceptions.GraphQlApiException;
import ocf.api.core.graphql.models.exceptions.GraphQlValidationException;

@Component
@Aspect
public class DefaultAspect {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* graphql.execution.DataFetcherExceptionHandler.*(..))")
	public void defaultExceptionHandlerPointcut() {
	}

	@Before(value = "defaultExceptionHandlerPointcut() and args(exceptionParameter)")
	public void logDgsException(JoinPoint joinPoint, DataFetcherExceptionHandlerParameters exceptionParameter) {
		if (exceptionParameter.getException() instanceof GraphQlApiException
				|| exceptionParameter.getException() instanceof GraphQlValidationException) {
			logger.error(MessageFormat.format("{0}, Handling with Custom Configuration",
					exceptionParameter.getException().toString()));
		} else {
			logger.error(MessageFormat.format(
					"Unhandled Exception occured: {0}, {1}, handling with default exception handler",
					exceptionParameter.getException().getClass().getSimpleName(),
					exceptionParameter.getException().getMessage()));
		}
	}

}

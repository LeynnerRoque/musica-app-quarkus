package org.music.app.business.fallbacks;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.music.app.business.service.AlbunsService;
import org.music.app.domain.model.Albuns;

@Slf4j
public class FallbackServiceHandler implements FallbackHandler<String> {
    @Override
    public String handle(ExecutionContext executionContext) {
        Throwable exception = executionContext.getFailure();
        log.warn("Failure occured : "+exception.getMessage());
        return "Error on create the service";
    }
}

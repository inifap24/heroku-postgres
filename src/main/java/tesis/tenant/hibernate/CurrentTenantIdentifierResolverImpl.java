package tesis.tenant.hibernate;

import com.google.common.base.Strings;
import tesis.tenant.util.TenantContextHolder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT_ID = "9999977";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant = TenantContextHolder.getTenant();
        return Strings.isNullOrEmpty(tenant) ? DEFAULT_TENANT_ID : tenant;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}

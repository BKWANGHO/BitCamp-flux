package store.ggun.ai.security.record;

import java.util.Set;

record ProfileResponse(String username, Set<String> roles) {
}
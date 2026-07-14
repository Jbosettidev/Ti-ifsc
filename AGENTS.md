# SecureByte - Cybersecurity Web App for Teens

**Academic Project (Integrative Work)**  
Accessible language for young people — the basics of digital security.

## Objective
Teach the essentials that matter on the internet — passwords, scams, phishing, and privacy — all in one place.  
The focus is on developing safe habits simply and progressively, recognising common risks (fake messages, false urgency, profile cloning), and offering practical activities with progress tracking and achievements.

## Topics Covered
Topic  Why It Matters 
Strong and unique passwords, Fewer accounts compromised in chain attacks
Phishing and suspicious links, Very common attack vector in email and social media
Urgency and "relative in trouble" scams, Social engineering that specifically targets teens
Privacy and what not to post, Less exposure to online abuse
Official apps and updates, Less malware and fewer fake apps

---
# Base Package
com.example.demo

# Domain Structure
Each domain (usuario, quiz, questao, fase, medalha, progresso) has its own package with:
- Entity
- Controller
- Service
- Repository

No DTOs. Endpoints return the entity directly in ResponseEntity.
Do not group entities into a single package.

# Build Tool
Maven

# Relationships
- N:N with no extra data -> @ManyToMany + @JoinTable, no new entity.
- N:N with extra data (progress, status, date, etc) -> create a join entity (e.g. UsuarioMedalha) inside the most relevant domain's package.
- Whenever a new class or package is needed, explicitly state the reason in the code/response.

# Serialization (no DTOs)
Bidirectional relationship -> use @JsonIgnoreProperties on the back-reference field, always including "hibernateLazyInitializer", "handler".

# URL Conventions
GET    /resources
GET    /resources/{id}
POST   /resources
PUT    /resources/{id}
DELETE /resources/{id}

Cross-domain relationship: /usuarios/{usuarioId}/medalhas, /usuarios/{usuarioId}/medalhas/{medalhaId}

**Rules:**
- plural, lowercase nouns
- no verbs in the URL
- /{id} for a specific resource
- HTTP method defines the operation
- every response is a ResponseEntity

# AI Instructions
- Follow existing architecture.
- Don't add new dependencies without request.
- Don't change package structure.
- No DTOs.
- Warn when a new class/package is needed and why.
- Join entity only if the relationship has extra attributes.
- Apply @JsonIgnoreProperties on bidirectional relationships.
- Reuse existing classes.
- No auth, caching, or extra config without explicit request.
- No unnecessary abstraction.
- Keep code simple and readable.
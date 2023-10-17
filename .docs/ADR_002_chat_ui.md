Context: Basic UI should be built using AI-based chat

Decision: Use React web app + embedded chat

Reason: Subjective decision

Alternatives:
- use Teams to communicate with Azure OpenAI

Consequences:
- use https://github.com/chatscope/chat-ui-kit-react to simplify build quickly UI
- use GraphQL to communicate webapi (web layer) with new UI
- expose WebService extensions to enable communication Chat <-> OpenAI <-> Commands/Queries extensions



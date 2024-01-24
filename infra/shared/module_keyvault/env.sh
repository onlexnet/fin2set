#!/bin/sh

# env.sh

# Change the contents of this output to get the environment variables
# of interest. The output must be valid JSON, with strings for both
# keys and values.
cat <<EOF
{
  "PLAID_CLIENT_ID": "$PLAID_CLIENT_ID",
  "PLAID_SECRET_DEVELOPMENT": "$PLAID_SECRET_DEVELOPMENT",
  "PLAID_SECRET_SANDBOX": "$PLAID_SECRET_SANDBOX",
  "CR_PAT": "$CR_PAT",
  "OPENAI_KEY": "$OPENAI_KEY",
  "OPENAI_ENDPOINT": "$OPENAI_ENDPOINT",
  "AUTH0_CLIENT_SECRET": "$AUTH0_CLIENT_SECRET"
}
EOF

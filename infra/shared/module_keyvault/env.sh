#!/bin/sh

# env.sh

# Change the contents of this output to get the environment variables
# of interest. The output must be valid JSON, with strings for both
# keys and values.
cat <<EOF
{
  "TF_VAR_NORDIGEN_SECRET_ID": "$TF_VAR_NORDIGEN_SECRET_ID",
  "TF_VAR_NORDIGEN_SECRET_KEY": "$TF_VAR_NORDIGEN_SECRET_KEY",
  "OPENAI_KEY": "$OPENAI_KEY",
  "OPENAI_ENDPOINT": "$OPENAI_ENDPOINT"
}
EOF

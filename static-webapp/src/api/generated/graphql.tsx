import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
const defaultOptions = {} as const;
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type Message = {
  __typename?: 'Message';
  text: Scalars['String']['output'];
};

export type MessageInput = {
  role: Role;
  text: Scalars['String']['input'];
};

export type Mutation = {
  __typename?: 'Mutation';
  newMessage: Message;
};


export type MutationNewMessageArgs = {
  messages: Array<MessageInput>;
};

export type Query = {
  __typename?: 'Query';
  view: Array<ViewEdge>;
};

export enum Role {
  Assistant = 'ASSISTANT',
  User = 'USER'
}

export type Subscription = {
  __typename?: 'Subscription';
  ticks: Scalars['Int']['output'];
  view: View;
};

export enum View {
  Chat = 'CHAT',
  View1 = 'VIEW1'
}

export type ViewEdge = {
  __typename?: 'ViewEdge';
  name: Scalars['String']['output'];
};

export type CounterSubscriptionVariables = Exact<{ [key: string]: never; }>;


export type CounterSubscription = { __typename?: 'Subscription', ticks: number };

export type NewMessageMutationVariables = Exact<{
  messages: Array<MessageInput> | MessageInput;
}>;


export type NewMessageMutation = { __typename?: 'Mutation', newMessage: { __typename?: 'Message', text: string } };

export type MyqueryQueryVariables = Exact<{ [key: string]: never; }>;


export type MyqueryQuery = { __typename?: 'Query', view: Array<{ __typename?: 'ViewEdge', name: string }> };


export const CounterDocument = gql`
    subscription counter {
  ticks
}
    `;

/**
 * __useCounterSubscription__
 *
 * To run a query within a React component, call `useCounterSubscription` and pass it any options that fit your needs.
 * When your component renders, `useCounterSubscription` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the subscription, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useCounterSubscription({
 *   variables: {
 *   },
 * });
 */
export function useCounterSubscription(baseOptions?: Apollo.SubscriptionHookOptions<CounterSubscription, CounterSubscriptionVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useSubscription<CounterSubscription, CounterSubscriptionVariables>(CounterDocument, options);
      }
export type CounterSubscriptionHookResult = ReturnType<typeof useCounterSubscription>;
export type CounterSubscriptionResult = Apollo.SubscriptionResult<CounterSubscription>;
export const NewMessageDocument = gql`
    mutation newMessage($messages: [MessageInput!]!) {
  newMessage(messages: $messages) {
    text
  }
}
    `;
export type NewMessageMutationFn = Apollo.MutationFunction<NewMessageMutation, NewMessageMutationVariables>;

/**
 * __useNewMessageMutation__
 *
 * To run a mutation, you first call `useNewMessageMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useNewMessageMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [newMessageMutation, { data, loading, error }] = useNewMessageMutation({
 *   variables: {
 *      messages: // value for 'messages'
 *   },
 * });
 */
export function useNewMessageMutation(baseOptions?: Apollo.MutationHookOptions<NewMessageMutation, NewMessageMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<NewMessageMutation, NewMessageMutationVariables>(NewMessageDocument, options);
      }
export type NewMessageMutationHookResult = ReturnType<typeof useNewMessageMutation>;
export type NewMessageMutationResult = Apollo.MutationResult<NewMessageMutation>;
export type NewMessageMutationOptions = Apollo.BaseMutationOptions<NewMessageMutation, NewMessageMutationVariables>;
export const MyqueryDocument = gql`
    query myquery {
  view {
    name
  }
}
    `;

/**
 * __useMyqueryQuery__
 *
 * To run a query within a React component, call `useMyqueryQuery` and pass it any options that fit your needs.
 * When your component renders, `useMyqueryQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useMyqueryQuery({
 *   variables: {
 *   },
 * });
 */
export function useMyqueryQuery(baseOptions?: Apollo.QueryHookOptions<MyqueryQuery, MyqueryQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<MyqueryQuery, MyqueryQueryVariables>(MyqueryDocument, options);
      }
export function useMyqueryLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<MyqueryQuery, MyqueryQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<MyqueryQuery, MyqueryQueryVariables>(MyqueryDocument, options);
        }
export type MyqueryQueryHookResult = ReturnType<typeof useMyqueryQuery>;
export type MyqueryLazyQueryHookResult = ReturnType<typeof useMyqueryLazyQuery>;
export type MyqueryQueryResult = Apollo.QueryResult<MyqueryQuery, MyqueryQueryVariables>;
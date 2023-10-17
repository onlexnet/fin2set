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

export type Query = {
  __typename?: 'Query';
  view: Array<ViewEdge>;
};

export type Subscription = {
  __typename?: 'Subscription';
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

export type MyqueryQueryVariables = Exact<{ [key: string]: never; }>;


export type MyqueryQuery = { __typename?: 'Query', view: Array<{ __typename?: 'ViewEdge', name: string }> };


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
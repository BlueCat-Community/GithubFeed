/*
 * Copyright 2019 Team Mulro in BlueCat-Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bluecat.githubfeed.model


// https://developer.github.com/v3/activity/starring/

// GET /users/:username/starred
data class starred(
    val id: String,
    val node_id: String,
    val name: String,
    val full_name: String,
    val owner: Stargazers,
    val private: Boolean,
    val html_url: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    val archive_url: String,
    val assignees_url: String,
    val blobs_url: String,
    val branches_url: String,
    val collaborators_url: String,
    val comments_url: String,
    val commits_url: String,
    val compare_url: String,
    val contents_url: String,
    val contributors_url: String,
    val deployments_url: String,
    val downloads_url: String,
    val events_url: String,
    val forks_url: String,
    val git_commits_url: String,
    val git_refs_url: String,
    val git_tags_url: String,
    val git_url: String,
    val issue_comment_url: String,
    val issue_events_url: String,
    val issues_url: String,
    val keys_url: String,
    val labels_url: String,
    val languages_url: String,
    val merges_url: String,
    val milestones_url: String,
    val notifications_url: String,
    val all: String,
    val participating: String,
    val pulls_url: String,
    val releases_url: String,
    val ssh_url: String,
    val stargazers_url: String,
    val statuses_url: String,
    val subscribers_url: String,
    val subscription_url: String,
    val tags_url: String,
    val teams_url: String,
    val trees_url: String,
    val clone_url: String,
    val mirror_url: String,
    val hooks_url: String,
    val svn_url: String,
    val homepage: String,
    val language: String?,
    val forks_count: Int,
    val stargazers_count: Int,
    val watchers_count: Int,
    val size: Int,
    val default_branch: String,
    val open_issues_count: Int,
    val is_template: Boolean,
    val topics: List<String>,
    val has_issues: Boolean,
    val has_projects: Boolean,
    val has_wiki: Boolean,
    val has_pages: Boolean,
    val has_downloads: Boolean,
    val archived: Boolean,
    val disabled: Boolean,
    val pushed_at: String,
    val created_at: String,
    val updated_at: String,
    val permission: List<Boolean>,
    val allow_rebase_merge: Boolean,
    val template_repository: Boolean,
    val allow_squash_merge: Boolean,
    val allow_merge_commit: Boolean,
    val subscribers_count: Int,
    val network_count: Int
)

// GET /repos/:owner/:repo/stargazers
data class Stargazers(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organization_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
)

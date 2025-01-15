import { client } from "./client";
import { AuthorizeEntity, APIInfo, APIPermission } from "../types";

// GET /api-manage/authorize-entity
export const GET_AUTHORIZE_ENTITIES = () => {
	return client.get<AuthorizeEntity[]>("/api-manage/authorize-entity");
};

// GET /api-manage/api-info
export const GET_ALL_APIS = () => {
	return client.get<APIInfo[]>("/api-manage/api-info");
};

// GET /api-manage/api-permission
export const GET_AUTHORIZED_APIS = (authorizeEntityKey: string) => {
	return client.get<APIPermission[]>("/api-manage/api-permission", {
		params: { authorizeEntity: authorizeEntityKey },
	});
};

// POST /api-manage/api-permission
export const ADD_PERMISSION = (authorizeEntityKey: string, apikey: string) => {
	return client.post("/api-manage/api-permission", null, {
		params: { authorizeEntity: authorizeEntityKey, apikey },
	});
};

// DELETE /api-manage/api-permission
export const DELETE_PERMISSION = (
	authorizeEntityKey: string,
	apikey: string
) => {
	return client.delete("/api-manage/api-permission", {
		params: { authorizeEntity: authorizeEntityKey, apikey },
	});
};

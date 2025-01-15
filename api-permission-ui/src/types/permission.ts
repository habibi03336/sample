export interface AuthorizeEntity {
	key: string;
	name: string;
	description: string;
}

export interface APIInfo {
	key: string;
	name: string;
	uri: string;
	method: string;
	description: string;
	packageName: string;
}

export interface APIPermission {
	id: number;
	authorizeEntity: string;
	apiKey: string;
}

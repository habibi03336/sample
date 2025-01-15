import { create } from "zustand";
import {
	APIInfo,
	APIPermission,
	AuthorizeEntity,
} from "../../types/permission";
import {
	GET_AUTHORIZE_ENTITIES,
	GET_ALL_APIS,
	GET_AUTHORIZED_APIS,
	ADD_PERMISSION,
	DELETE_PERMISSION,
} from "../../api/apiPermission";

interface PermissionStore {
	// 상태
	authorizeEntities: AuthorizeEntity[];
	apiInfos: APIInfo[];
	apiPermissions: APIPermission[];
	selectedApis: APIInfo[];
	selectedAuthorizeEntity: AuthorizeEntity | null;

	// 로딩 상태
	loading: boolean;

	// AuthorizeEntity 관련 액션
	fetchAuthorizeEntities: () => Promise<void>;

	// APIInfo 관련 액션
	fetchAPIInfos: () => Promise<void>;

	// APIPermission 관련 액션
	fetchAPIPermissions: (authorizeEntityKey: string) => Promise<void>;
	addAPIPermission: (
		authorizeEntityKey: string,
		apiKey: string
	) => Promise<void>;
	deleteAPIPermission: (
		authorizeEntityKey: string,
		apiKey: string
	) => Promise<void>;
	setSelectedApis: (apis: APIInfo[]) => void;
	updateApiPermissions: (changes: {
		addedApikeys: string[];
		removedApikeys: string[];
	}) => Promise<void>;
	setSelectedAuthorizeEntity: (entity: AuthorizeEntity) => void;
}

export const usePermissionStore = create<PermissionStore>((set, get) => ({
	// 초기 상태
	authorizeEntities: [],
	apiInfos: [],
	apiPermissions: [],
	selectedApis: [],
	loading: false,
	selectedAuthorizeEntity: null,

	// AuthorizeEntity 조회
	fetchAuthorizeEntities: async () => {
		set({ loading: true });
		try {
			const response = await GET_AUTHORIZE_ENTITIES();
			set({ authorizeEntities: response.data });
		} catch (error) {
			console.error("Failed to fetch authorize entities:", error);
		} finally {
			set({ loading: false });
		}
	},

	// APIInfo 조회
	fetchAPIInfos: async () => {
		set({ loading: true });
		try {
			const response = await GET_ALL_APIS();
			set({ apiInfos: response.data });
		} catch (error) {
			console.error("Failed to fetch API infos:", error);
		} finally {
			set({ loading: false });
		}
	},

	// APIPermission 조회
	fetchAPIPermissions: async (authorizeEntityKey: string) => {
		set({ loading: true });
		try {
			const response = await GET_AUTHORIZED_APIS(authorizeEntityKey);
			set({ apiPermissions: response.data });
		} catch (error) {
			console.error("Failed to fetch API permissions:", error);
		} finally {
			set({ loading: false });
		}
	},

	// APIPermission 추가
	addAPIPermission: async (authorizeEntityKey: string, apiKey: string) => {
		set({ loading: true });
		try {
			await ADD_PERMISSION(authorizeEntityKey, apiKey);
			// 권한 목록 다시 조회
			await get().fetchAPIPermissions(authorizeEntityKey);
		} catch (error) {
			console.error("Failed to add API permission:", error);
		} finally {
			set({ loading: false });
		}
	},

	// APIPermission 삭제
	deleteAPIPermission: async (authorizeEntityKey: string, apiKey: string) => {
		set({ loading: true });
		try {
			await DELETE_PERMISSION(authorizeEntityKey, apiKey);
			// 권한 목록 다시 조회
			await get().fetchAPIPermissions(authorizeEntityKey);
		} catch (error) {
			console.error("Failed to delete API permission:", error);
		} finally {
			set({ loading: false });
		}
	},

	setSelectedApis: (apis) => set({ selectedApis: apis }),

	updateApiPermissions: async ({
		addedApikeys,
		removedApikeys,
	}: {
		addedApikeys: string[];
		removedApikeys: string[];
	}) => {
		const { selectedAuthorizeEntity } = get();
		if (!selectedAuthorizeEntity) return;

		set({ loading: true });
		try {
			// 병렬로 추가 및 삭제 요청 실행
			await Promise.all([
				...addedApikeys.map((apiKey) =>
					ADD_PERMISSION(selectedAuthorizeEntity.key, apiKey)
				),
				...removedApikeys.map((apiKey) =>
					DELETE_PERMISSION(selectedAuthorizeEntity.key, apiKey)
				),
			]);

			// 권한 목록 다시 조회
			await get().fetchAPIPermissions(selectedAuthorizeEntity.key);
		} catch (error) {
			console.error("Failed to update API permissions:", error);
		} finally {
			set({ loading: false });
		}
	},

	setSelectedAuthorizeEntity: (entity) => {
		set({ selectedAuthorizeEntity: entity });
		if (entity) {
			get().fetchAPIPermissions(entity.key);
		}
	},
}));

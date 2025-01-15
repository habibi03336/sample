import { useEffect } from "react";
import { usePermissionStore } from "./store/apiPermissionStore";
import { Table } from "antd";

const AuthorizeEntityList: React.FC = () => {
	const {
		authorizeEntities,
		loading,
		setSelectedAuthorizeEntity,
		selectedAuthorizeEntity,
		fetchAuthorizeEntities,
	} = usePermissionStore();

	useEffect(() => {
		fetchAuthorizeEntities();
	}, [fetchAuthorizeEntities]);

	const columns = [
		{
			title: "엔티티 이름",
			dataIndex: "name",
			key: "name",
		},
	];

	return (
		<div>
			<h2>권한 엔티티 목록</h2>
			<Table
				columns={columns}
				dataSource={authorizeEntities}
				rowKey="key"
				loading={loading}
				onRow={(authorizeEntity) => ({
					onClick: () => setSelectedAuthorizeEntity(authorizeEntity),
				})}
				style={{ cursor: "pointer" }}
				rowSelection={{
					type: "radio",
					selectedRowKeys: selectedAuthorizeEntity
						? [selectedAuthorizeEntity.key]
						: [],
					onChange: (_, [selectedRow]) => {
						setSelectedAuthorizeEntity(selectedRow);
					},
					columnWidth: 0,
					renderCell: () => null, // 라디오 버튼 렌더링 안 함
				}}
			/>
		</div>
	);
};

export default AuthorizeEntityList;

import { useState, useEffect } from "react";
import { Table, Button, Space } from "antd";
import { usePermissionStore } from "./store/apiPermissionStore";
import { APIInfo } from "../types";
import { TableRowSelection } from "antd/es/table/interface";

const APIList: React.FC = () => {
	const { apiInfos, updateApiPermissions, apiPermissions, fetchAPIInfos } =
		usePermissionStore();
	const [selectedApikeys, setSelectedApikeys] = useState<string[]>([]);
	const [initialApiPaths, setInitialApiPaths] = useState<string[]>([]);

	const columns = [
		{
			title: "API 경로",
			dataIndex: "uri",
			key: "uri",
		},
		{
			title: "HTTP 메서드",
			dataIndex: "method",
			key: "method",
		},
		{
			title: "설명",
			dataIndex: "description",
			key: "description",
		},
	];

	useEffect(() => {
		fetchAPIInfos();
	}, [fetchAPIInfos]);

	useEffect(() => {
		if (apiPermissions) {
			const existingApiPaths = apiPermissions.map((api) => api.apiKey);
			setSelectedApikeys(existingApiPaths);
			setInitialApiPaths(existingApiPaths);
		}
	}, [apiPermissions]);

	const rowSelection: TableRowSelection<APIInfo> = {
		selectedRowKeys: selectedApikeys,
		onChange: (_: React.Key[], selectedRows: APIInfo[]) => {
			setSelectedApikeys(selectedRows.map((api) => api.key));
		},
	};

	const handleSave = () => {
		const addedApikeys = apiInfos
			.map((api) => api.key)
			.filter(
				(apikey) =>
					selectedApikeys.includes(apikey) && !initialApiPaths.includes(apikey)
			);

		const removedApikeys = initialApiPaths.filter(
			(key) => !selectedApikeys.includes(key)
		);

		updateApiPermissions({
			addedApikeys,
			removedApikeys,
		});
	};

	return (
		<div>
			<Space direction="vertical" style={{ width: "100%" }}>
				<div
					style={{
						display: "flex",
						justifyContent: "space-between",
						alignItems: "center",
					}}
				>
					<h2>API 목록</h2>
					<Button type="primary" onClick={handleSave}>
						API 허가 저장
					</Button>
				</div>
				<Table
					rowSelection={rowSelection}
					dataSource={apiInfos}
					columns={columns}
					rowKey="key"
					pagination={{
						defaultPageSize: 10,
						showSizeChanger: true,
						showTotal: (total) => `총 ${total}개 API`,
					}}
				/>
			</Space>
		</div>
	);
};

export default APIList;
